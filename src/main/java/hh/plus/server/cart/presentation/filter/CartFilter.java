package hh.plus.server.cart.presentation.filter;

import jakarta.servlet.annotation.WebFilter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter("/api/cart/*")
public class CartFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

}
