import br.hackthon.account.AccountStart;
import br.hackthon.account.commons.HTTPCall;
import br.hackthon.account.commons.JsonUtil;
import br.hackthon.account.commons.LoginDTO;
import br.hackthon.account.commons.Security;
import br.hackthon.drugstore.order.OrderStart;
import br.hackthon.drugstore.order.model.entities.Order;
import br.hackthon.drugstore.order.model.entities.nested.Item;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

/**
 *
 *  Simulation process teste
 *
 *  Fisrt  step Login
 *  Second step order some drug
 *
 */
public class SimulationProccessTest {

    private static final String ENDPOINT_USER = "http://localhost:9001";

    private static final String ENDPOINT_ORDER = "http://localhost:9003";


    @BeforeClass
    public static void setup() {

        AccountStart.main(new String[]{});

        OrderStart.main(new String[]{});
    }


    @Test
    public void signIn() {
        String toSignIn = JsonUtil.getAsJson(new LoginDTO("tst.order", "123"));

        String token = HTTPCall.doPostAndReturnJson(ENDPOINT_USER.concat("/usr/signin"), toSignIn);
        Map<String, String> headerValues = Collections.singletonMap(Security.HEADER_AUTH, token);

        Order order = new Order();

        order.setAccountName( "TST.Order02" );

        order.setAccountCoordinates( new Point(
                new Position(49.88709760, -97.16837690)) );

        order.setDrugstoreName(" All Drugs to cure you");

        order.setAccountCoordinates( new Point(
                new Position(49.88709860, -97.16837890)) );

        order.addItem( new Item("drug 01", 1 ) );
        order.addItem( new Item("drug 02", 2 ) );

        String orderJsonDATA = JsonUtil.getAsJson( order );


        HTTPCall.doPostAndReturnJson(ENDPOINT_ORDER.concat("/order"), orderJsonDATA, headerValues);
    }
}
