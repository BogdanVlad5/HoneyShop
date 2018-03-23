package com.honeyshop.services;

import com.honeyshop.dao.SaleDao;
import com.honeyshop.dao.ShoppingCartDao;
import com.honeyshop.models.Sale;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.services.generic.GenericServiceImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SaleService extends GenericServiceImpl<Sale>{

}
