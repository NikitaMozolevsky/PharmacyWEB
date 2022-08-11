package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.Message.PRODUCT_WAS_ADDED_MSG;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRODUCT_WAS_ADDED;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.GOODS_QUANTITY;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_ID;

public class AddProductQuantityCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        Map<String, String> productInfo = new HashMap<>();
        productInfo.put(PRODUCT_ID, request.getParameter(PRODUCT_ID));
        productInfo.put(GOODS_QUANTITY, request.getParameter(GOODS_QUANTITY));
        try {
            productService.addToProductQuantity(productInfo);
            request.setAttribute(PRODUCT_WAS_ADDED, PRODUCT_WAS_ADDED_MSG);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return new ShowProductListCommand().execute(request);
    }
}
