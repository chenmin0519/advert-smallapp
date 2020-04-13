package com.advert.smallapp.service.impl;

import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.enums.TypeEnum;
import com.advert.smallapp.mapper.ContactMapper;
import com.advert.smallapp.mapper.GoodsDetailMapper;
import com.advert.smallapp.mapper.GoodsMapper;
import com.advert.smallapp.pojo.Contact;
import com.advert.smallapp.pojo.Goods;
import com.advert.smallapp.pojo.GoodsDetail;
import com.advert.smallapp.service.GoodsService;
import com.advert.smallapp.tdo.GoodsSaveDto;
import com.advert.smallapp.tdo.GoodsVo;
import com.advert.smallapp.utils.OSSClientUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private OSSClientUtils ossClient;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    @Override
    public PageInfo<Goods> loadPage(PageQuery<Goods> query) {
        return PageHelper.startPage(query.getPageNum(), query.getPageSize()).doSelectPageInfo(
                () -> goodsMapper.select(query.getQueryPo()));
    }

    @Override
    public GoodsVo loadById(Long id) {
        GoodsVo result = new GoodsVo();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(goods,result);
        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setGoodsId(goods.getId());
        goodsDetail = goodsDetailMapper.selectOne(goodsDetail);
        result.setContent(goodsDetail.getContent());
        result.setImages(goodsDetail.getImages());
        Contact contact = new Contact();
        contact = contactMapper.selectByPrimaryKey(goods.getContactId());
        result.setName(contact.getName());
        result.setNumber(contact.getNumber());
        result.setTypeName(TypeEnum.getByCode(goods.getCategory()).getValue());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GoodsSaveDto saveDto) {
        Contact contact = new Contact();
        contact.setNumber(saveDto.getNumber());
        contact.setName(saveDto.getName());
        contactMapper.insertSelective(contact);
        Goods goods = new Goods();
        BeanUtils.copyProperties(saveDto,goods);
        goods.setContactId(contact.getId());
        goodsMapper.insertSelective(goods);
//        String imagePaths = imagesImport(goods.getId(),saveDto.getImages());
        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setImages(saveDto.getImages());
        goodsDetail.setContent(saveDto.getContent());
        goodsDetail.setGoodsId(goods.getId());
        goodsDetailMapper.insertSelective(goodsDetail);
        List<String> images = JSONObject.parseObject(saveDto.getImages(),ArrayList.class);
        goods.setIcon(images.get(0));
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 图片保存
     * @param id
     * @param images
     */
    private String imagesImport(Long id, List<String> images) {
        List<String> paths = new ArrayList<>();
        int count = 0;
        for(String path : images){
            count ++;
            String uploadWebFilePath = ossClient.uploadWebFile(path,"/images/goods",id+count+"");
            paths.add(uploadWebFilePath);
        }
        return JSONObject.toJSONString(paths);
    }

}
