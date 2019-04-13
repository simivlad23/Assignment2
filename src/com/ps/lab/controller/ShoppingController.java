package com.ps.lab.controller;



import com.ps.lab.Main;
import com.ps.lab.model.Item;
import com.ps.lab.model.ShoppingBasket;
import com.ps.lab.service.ContextHolder;
import com.ps.lab.service.ItemService;
import com.ps.lab.service.ShoppingBasketService;
import com.ps.lab.utils.DataConverter;
import com.ps.lab.view.ShoppingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ShoppingController {

    private final ShoppingView shoppingView;
    private final DataConverter dataConverter;
    private final ItemService itemService;
    private final ShoppingBasketService shoppingBasketService;
    private final ContextHolder contextHolder;

    public ShoppingController(ShoppingView shoppingView,
                              DataConverter dataConverter,
                              ItemService itemService,
                              ShoppingBasketService shoppingBasketService,
                              ContextHolder contextHolder) {
        this.shoppingView = shoppingView;
        this.dataConverter = dataConverter;
        this.itemService = itemService;
        this.shoppingBasketService = shoppingBasketService;
        this.contextHolder = contextHolder;

        //collect initial view data
        List<Item> items = this.itemService.findAll();
        ShoppingBasket shoppingBasket = this.shoppingBasketService.findByUserId(contextHolder.getCurrentUser().getId());

        Object[][] itemData = this.dataConverter.itemToTableData(items);
        String[] itemColumnNames = this.dataConverter.itemToTableColumnNames();


        Object[][] shoppingBasketData = dataConverter.shoppingBasketToTableData(shoppingBasket);
        String[] shoppingBasketArticlesColumnNames = dataConverter.shoppingBasketToTableColumnNames();

        //set data
        this.shoppingView.setLoggedInUser(contextHolder.getCurrentUser().getUsername());
        this.shoppingView.refreshArticleTable(itemData, itemColumnNames);
        this.shoppingView.refreshShoppingBasketTable(shoppingBasketData, shoppingBasketArticlesColumnNames);
        this.shoppingView.refreshBasketSelection(Arrays.asList(shoppingBasket));

        //add action listeners
        this.shoppingView.addAddToBasketActionListener(new AddToBasketActionListener());
        this.shoppingView.addLogoutActionListener(new LogoutActionListener());
        this.shoppingView.addSelectBasketActionListener(new SelectBasketActionListener());
        this.shoppingView.setVisible(true);
    }

    private class AddToBasketActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int quantity = shoppingView.getQuantity();
            Long selectedArticleId = shoppingView.getSelectedArticleId();
            Long selectedShoppingBasketId = shoppingView.getSelectedShoppingBasketId();

            shoppingBasketService.addItem(selectedShoppingBasketId, selectedArticleId, quantity);
            refreshSelectedShoppingBasket();
        }
    }

    private class LogoutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            contextHolder.setCurrentUser(null);
            shoppingView.setVisible(false);
            Main.openLogin();
        }
    }

    private class SelectBasketActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            refreshSelectedShoppingBasket();
        }
    }

    private void refreshSelectedShoppingBasket() {
        Long selectedShoppingBasketId = shoppingView.getSelectedShoppingBasketId();
        ShoppingBasket shoppingBasket = shoppingBasketService.findById(selectedShoppingBasketId);

        String[] shoppingBasketArticlesColumnNames = dataConverter.shoppingBasketToTableColumnNames();
        Object[][] shoppingBasketData = dataConverter.shoppingBasketToTableData(shoppingBasket);

        shoppingView.refreshSelectedShoppingBasket(shoppingBasket,
                shoppingBasketData, shoppingBasketArticlesColumnNames);
    }
}
