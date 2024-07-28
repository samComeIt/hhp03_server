package hh.plus.server.balance.presentation.filter;

import jakarta.servlet.annotation.WebFilter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter("/api/balance/*")
public class BalanceFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

}
