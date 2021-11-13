package com.gotravel.flightshoppingservice.cache;

import com.gotravel.flightshoppingservice.model.SearchRequest;
import com.gotravel.flightshoppingservice.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Component("searchCacheKeyGenerator")
public class SearchCacheKeyGenerator implements KeyGenerator {

       public Object generate(final Object target, final Method method, final Object... params) {
           SearchRequest request = (SearchRequest) params[0];
           return request.getDepartureAirport().concat(request.getArrivalAirport())
                   .concat(getDepartureDay(request))
                   .concat(getReturnDay(request));
        }

    private String getDepartureDay(final SearchRequest request) {
           return String.valueOf(DateUtil.convertToDay(request.getDepartureDate()));
    }

    private String getReturnDay(final SearchRequest request) {
           return Objects.nonNull(request.getReturnDate())
                   ? String.valueOf(DateUtil.convertToDay(request.getReturnDate()))
                   : StringUtils.EMPTY;
    }

}
