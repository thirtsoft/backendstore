package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Stock;
import com.wokite.net.backendstore.repository.StockRepository;
import com.wokite.net.backendstore.services.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long stockId, Stock stock) {
        if (!stockRepository.existsById(stockId)) {
            throw new ResourceNotFoundException("Stock Not found");
        }
        Optional<Stock> stockReference = stockRepository.findById(stockId);
        if (!stockReference.isPresent()) {
            throw new ResourceNotFoundException("Stock Not found");
        }
        Stock stockResultat = stockReference.get();

        stockResultat.setQuantite(stock.getQuantite());
        stockResultat.setProduct(stock.getProduct());

        return stockRepository.save(stockResultat);
    }

    @Override
    public Optional<Stock> findStockById(Long stockId) {
        if (!stockRepository.existsById(stockId)) {
            throw new ResourceNotFoundException("Stock Not found");
        }
        return stockRepository.findById(stockId);
    }

    @Override
    public Stock findStockByQuantite(int quantite) {
        return stockRepository.findByQuantite(quantite);
    }

    @Override
    public List<Stock> findListStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> findListStockByProductId(Long prodId) {
        return stockRepository.findListStockByProductId(prodId);
    }

    @Override
    public void deleteStock(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("stock not found");
        }
        stockRepository.deleteById(id);
    }
}
