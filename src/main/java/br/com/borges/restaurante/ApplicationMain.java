package br.com.borges.restaurante;

import br.com.borges.restaurante.dao.MenuDao;
import br.com.borges.restaurante.dao.OrderDao;
import br.com.borges.restaurante.entity.*;
import br.com.borges.restaurante.service.*;
import br.com.borges.restaurante.utils.JpaUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationMain {
    public static void main(String[] args) {

        EntityManager entityManager = JpaUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Category category = createCategory(entityManager);

        createMenu(category, entityManager);

        Client client = createClient(entityManager);

        Order order = OrderService.createOrder(entityManager,client);

        MenuDao menuDao = new MenuDao(entityManager);
        Menu picanha = menuDao.findByDishName("Picanha Paulista");
        Menu galinhada = menuDao.findByDishName("galinhada");

        assert picanha != null;
        OrderDishes dishePicanha = new OrderDishes(order,picanha,3);
        OrderDishesService.saveOrderDishes(entityManager,dishePicanha);

        assert galinhada != null;
        OrderDishes disheGalinhada = new OrderDishes(order,galinhada,2);
        OrderDishesService.saveOrderDishes(entityManager,disheGalinhada);

        order.addOrderDishes(dishePicanha);
        order.addOrderDishes(disheGalinhada);
        order.setTotalValue(dishePicanha.getOrderValue());
        order.setTotalValue(disheGalinhada.getOrderValue());

        OrderDao orderDao = new OrderDao(entityManager);
        orderDao.update(order);

        formaterOrder(order);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static Client createClient(EntityManager entityManager) {
        Client client = new Client("102.012.211.12","Borges", 33225478);
        ClientService.saveClient(entityManager,client);
        return client;
    }

    private static Category createCategory(EntityManager entityManager) {
        Category category = new Category();
        category.setDescription("Prato principal");
        CategoryService.saveCategory(entityManager, category);
        return category;
    }

    private static void createMenu(Category category, EntityManager entityManager) {
        Menu picanha = new Menu();
        picanha.setName("Picanha Paulista");
        picanha.setDescription("Picanha com tomate e queijo mussarela");
        picanha.setAvailable(true);
        picanha.setValueDish(BigDecimal.valueOf(45.00));
        picanha.setCategory(category);

        Menu galinhada = new Menu();
        galinhada.setName("Galinhada");
        galinhada.setDescription("Galinhada com arroz e legumes");
        galinhada.setAvailable(true);
        galinhada.setValueDish(BigDecimal.valueOf(15.00));
        galinhada.setCategory(category);

        MenuService.saveMenu(entityManager, picanha);
        MenuService.saveMenu(entityManager, galinhada);
    }

    private static void formaterOrder(Order order){
        System.out.println("########################### RASFOOD ###########################");
        System.out.println("Numero pedido: " + order.getId());
        System.out.println("------------------------------------------------------------");
        System.out.println("Data pedido: " + formaterDate(order.getDtOrder()));
        System.out.println("------------------------------------------------------------");
        System.out.println("Cliente: " + order.getClient().getName() + " - Fone:" + order.getClient().getPhone());
        System.out.println("------------------------------------------------------------");
        System.out.println("Itens pedido: ");
        order.getOrderDishes().forEach(item -> {
            System.out.println("*** " + item.getMenu().getName() + " ---- R$ " + item.getMenu().getValueDish()
                    + " X " + item.getQuantity() + " = R$ " + item.getOrderValue()) ;
        });
        System.out.println("------------------------------------------------------------");
        System.out.println("Total pedido: R$ " + order.getAmount());
        BigDecimal txService = order.getAmount().multiply(BigDecimal.valueOf(0.1)).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Taxa serviço: R$ " + txService);
        System.out.println("Total: R$ " + order.getAmount().add(txService));
        System.out.println("############################# FIM #############################");
    }

    private static String formaterDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return date.format(formatter);
    }
}
