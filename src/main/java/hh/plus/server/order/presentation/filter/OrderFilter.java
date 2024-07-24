package hh.plus.server.order.presentation.filter;

import jakarta.servlet.annotation.WebFilter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter("/api/cart/*")
public class OrderFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

}
