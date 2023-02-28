package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.StockApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Stock;
import com.wokite.net.backendstore.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class StockController implements StockApi {

    @Autowired
    private StockService stockService;

    @Override
    public ResponseEntity<Stock> createStock(Stock stock) {
        stock.setDateMiseAJour(new Date());
        Stock stockResult = stockService.saveStock(stock);
        return new ResponseEntity<>(stockResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Stock> updateStock(Long stockId, Stock stock) {
        stock.setId(stockId);
        stock.setDateMiseAJour(new Date());
        Stock stockResult = stockService.saveStock(stock);
        return new ResponseEntity<>(stockResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Stock> getStockById(Long id) {
        Stock stockResult = stockService.findStockById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock " + id + "not found"));
        return new ResponseEntity<>(stockResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stockList = stockService.findListStocks();
        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Stock>> getListStocksByProductId(Long prodId) {
        List<Stock> stockList = stockService.findListStockByProductId(prodId);
        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteStock(Long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.ok()
                .build();
    }
}
