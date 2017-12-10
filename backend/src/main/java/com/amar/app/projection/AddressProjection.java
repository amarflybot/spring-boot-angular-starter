package com.amar.app.projection;

import com.amar.app.model.Address;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by amarendra on 04/12/17.
 */
@Projection(name = "inLineData", types = {Address.class})
public interface AddressProjection {

    String getStreetName();

    String getCountry();

}
