package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.ProductDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

import static by.mozolevskij.pharmacy.command.attribute.PagePath.SHOW_PRODUCTS_JSP;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_ID;

public class DeleteProductCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductDaoImpl productDao = ProductDaoImpl.getInstance();
        int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));
        try {
            productDao.deleteById(productId);
        } catch (DaoException e) {
            throw new CommandException();
        }
        return new ShowProductListCommand().execute(request);
    }
}
