package com.ps.lab.view;

import com.ps.lab.model.Item;
import com.ps.lab.model.ShoppingBasket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingView extends JFrame {
    private static final String TITLE = "Article View";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private final JLabel currentUserLabel;
    private final JLabel shoppingBasketName;
    private final JLabel shoppingBasketTotalPrice;
    private final JButton actionBtn;
    private final JButton logout;
    private final JTable articleTable;
    private final JTable shoppingBasketTable;
    private final JComboBox select;
    private final SpinnerNumberModel quantity;
    private final JSpinner spinner;
    private final JPanel left;
    private final JPanel right;

    public ShoppingView() throws HeadlessException {
        super(TITLE);
        currentUserLabel = new JLabel("-empty-");
        shoppingBasketName = new JLabel("-empty-");
        shoppingBasketTotalPrice = new JLabel("-empty-");
        actionBtn = new JButton("Action");
        logout = new JButton("Logout");
        articleTable = new JTable();
        shoppingBasketTable = new JTable();
        select = new JComboBox<>(new String[]{"Id", "Name", "Price"});
        select.setEditable(false);
        quantity = new SpinnerNumberModel();
        spinner = new JSpinner(quantity);
        left = new JPanel();
        right = new JPanel();
        initializeView();
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeView() {

        left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        left.add(Box.createRigidArea(new Dimension(20, 20)));
        currentUserLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        left.add(currentUserLabel);
        left.add(Box.createRigidArea(new Dimension(20, 20)));
        JScrollPane jScrollPaneArticles = new JScrollPane(articleTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneArticles.setBorder(new EmptyBorder(10, 10, 10, 10));
        left.add(jScrollPaneArticles);
        left.add(Box.createRigidArea(new Dimension(20, 20)));
        spinner.setBorder(new EmptyBorder(10, 10, 10, 10));
        left.add(spinner);
        left.add(Box.createRigidArea(new Dimension(20, 20)));
        select.setBorder(new EmptyBorder(10, 10, 10, 10));
        left.add(select);
        left.add(Box.createRigidArea(new Dimension(20, 20)));
        left.add(actionBtn);
        left.add(Box.createRigidArea(new Dimension(20, 20)));
        left.add(logout);
        add(left);

        right.add(Box.createRigidArea(new Dimension(20, 20)));
        shoppingBasketName.setBorder(new EmptyBorder(10, 10, 10, 10));
        right.add(shoppingBasketName);
        add(right);
        right.add(Box.createRigidArea(new Dimension(20, 20)));
        JScrollPane jScrollPaneShoppingBasket = new JScrollPane(shoppingBasketTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneShoppingBasket.setBorder(new EmptyBorder(10, 10, 10, 10));
        right.add(jScrollPaneShoppingBasket);
        right.add(Box.createRigidArea(new Dimension(20, 20)));
        right.add(shoppingBasketTotalPrice);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }

    public void setLoggedInUser(String userName) {
        this.currentUserLabel.setText("Logged in as: " + userName);
    }

    public void refreshArticleTable(Object[][] data, String[] columnNames) {
        DefaultTableModel tableModel = (DefaultTableModel) articleTable.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void refreshShoppingBasketTable(Object[][] data, String[] columnNames) {
        DefaultTableModel tableModel = (DefaultTableModel) shoppingBasketTable.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void refreshBasketSelection(List<ShoppingBasket> shoppingBaskets) {
        select.removeAllItems();
        for (ShoppingBasket shoppingBasket : shoppingBaskets) {
            select.addItem(shoppingBasket);
        }
    }

    public void addAddToBasketActionListener(ActionListener listener) {
        actionBtn.addActionListener(listener);
    }

    public void addLogoutActionListener(ActionListener listener) {
        logout.addActionListener(listener);
    }

    public void addSelectBasketActionListener(ActionListener listener) {
        select.addActionListener(listener);
    }

    public Long getSelectedArticleId() {
        return (Long) articleTable.getValueAt(articleTable.getSelectedRow(), 0);
    }

    public Long getSelectedShoppingBasketId() {
        Object selectedItem = select.getSelectedItem();
        ShoppingBasket nameIdDTO = ShoppingBasket.class.cast(selectedItem);

        assert nameIdDTO != null;
        return nameIdDTO.getId();
    }

    public int getQuantity() {
        return quantity.getNumber().intValue();
    }

    public void refreshSelectedShoppingBasket(ShoppingBasket shoppingBasket,
                                              Object[][] data, String[] columnNames) {
        refreshShoppingBasketTable(data, columnNames);

        shoppingBasketTotalPrice.setText(String.valueOf(shoppingBasket.getItems().stream().mapToDouble(o -> o.getItem().getPrice()).sum()));
        shoppingBasketName.setText("Basket name: " + shoppingBasket.getUser().getUsername());
    }
}
