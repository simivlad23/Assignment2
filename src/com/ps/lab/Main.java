package com.ps.lab;

import com.ps.lab.controller.ShoppingController;
import com.ps.lab.repository.ItemRepository;
import com.ps.lab.repository.ShoppingBasketItemRepository;
import com.ps.lab.repository.ShoppingBasketRepository;
import com.ps.lab.repository.UserRepository;
import com.ps.lab.repository.impl.*;
import com.ps.lab.service.ContextHolder;
import com.ps.lab.service.ItemService;
import com.ps.lab.service.ShoppingBasketService;
import com.ps.lab.service.UserService;
import com.ps.lab.service.impl.*;
import com.ps.lab.utils.DataConverter;
import com.ps.lab.utils.impl.DataConverterImpl;
import com.ps.lab.view.LoginView;
import com.ps.lab.view.ShoppingView;

public class Main {

    private static final String SCHEMA_NAME = "magazin";

    private static DataConverter dataConverter;
    private static UserService userService;
    private static ItemService itemService;
    private static ContextHolder contextHolder;
    private static ShoppingController shoppingController;
    private static ShoppingBasketService shoppingBasketService;
    private static ShoppingView shoppingView;

    public static void main(String[] args) {

        JDBConnectionWrapper jdbConnectionWrapper = new JDBConnectionWrapper(SCHEMA_NAME);

        dataConverter = new DataConverterImpl();

        //initialize repo
        ItemRepository itemRepository = new ItemRepositoryImpl(jdbConnectionWrapper);
        UserRepository userRepository = new UserRepositoryImpl(jdbConnectionWrapper);
        ShoppingBasketItemRepository shoppingBasketItemRepository = new ShoppingBasketItemRepositoryImpl(jdbConnectionWrapper);
        ShoppingBasketRepository shoppingBasketRepository = new ShoppingBasketRepositoryImpl(jdbConnectionWrapper,
                shoppingBasketItemRepository);

        //initialize service
        userService = new UserServiceImpl(userRepository);
        itemService = new ItemServiceImpl(itemRepository);
        shoppingBasketService = new ShoppingBasketServiceImpl(itemRepository,
                shoppingBasketItemRepository,
                shoppingBasketRepository);
        ShoppingBasketService shoppingBasketDecorator = new ShoppingBasketDecoratorImpl(shoppingBasketService);
        contextHolder = new ContextHolderImpl();

        //view
        shoppingView = new ShoppingView();

        openLogin();
    }

    public static void openUserView() {
        shoppingController = new ShoppingController(shoppingView,
                dataConverter,
                itemService,
                shoppingBasketService,
                contextHolder);
    }

    public static void openLogin() {
        LoginView loginView = new LoginView(userService, contextHolder);
        loginView.setVisible(true);
    }
}
