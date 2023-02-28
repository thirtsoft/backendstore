package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {

    Stock saveStock(Stock stock);

    Stock updateStock(Long stockId, Stock stock);

    Optional<Stock> findStockById(Long stockId);

    Stock findStockByQuantite(int quantite);

    List<Stock> findListStocks();

    List<Stock> findListStockByProductId(Long prodId);

    void deleteStock(Long id);


}
