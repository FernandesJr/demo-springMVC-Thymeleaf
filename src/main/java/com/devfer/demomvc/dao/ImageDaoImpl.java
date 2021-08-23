package com.devfer.demomvc.dao;

import com.devfer.demomvc.domain.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends AbstractDao<Image, Long> implements ImageDao{
}
