package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.*;

public class AddProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        Map<String, String> newProductData = new HashMap<String, String>();
        newProductData.put(PRODUCT_NAME, request.getParameter(PRODUCT_NAME));
        newProductData.put(DETAILS, request.getParameter(DETAILS));
        newProductData.put(PRICE, request.getParameter(PRICE));
        newProductData.put(TYPE, request.getParameter(TYPE));
        newProductData.put(PHOTO, request.getParameter(PHOTO));
        newProductData.put(GOODS_QUANTITY, request.getParameter(GOODS_QUANTITY));
        newProductData.put(NEED_PRESCRIPTION, request.getParameter(NEED_PRESCRIPTION));

        //if (userService.validateUserData(userDataMap)) {
        try {
            productService.addProduct(newProductData);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        //}
        logger.log(Level.INFO, "product was added successful");
        /*router.setPage(SHOW_PRODUCTS);*/
        return new ShowProductListCommand().execute(request);
    }
}
