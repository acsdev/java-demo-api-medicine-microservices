package br.hackthon.account;

import br.hackthon.account.commons.JsonUtil;
import br.hackthon.account.commons.LoginDTO;
import br.hackthon.account.commons.Security;
import br.hackthon.account.model.jooq.tables.pojos.Account;
import br.hackthon.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;


public class AccountRoutes {

    private static final Logger LOG = LoggerFactory.getLogger(AccountRoutes.class);

    public static Route singUp = (request, response) -> {

        Account in = JsonUtil.getAsObject(request.body(), Account.class);

        Account out  = new AccountService().save( in );

        if (LOG.isDebugEnabled()) {
            LOG.error( JsonUtil.getAsJson(out) );
        }


        return out;

    };

    public static Route singIn = (request, response) -> {

        LoginDTO loginDTO = JsonUtil.getAsObject(request.body(), LoginDTO.class);
        String passOnDB = new AccountService().findPassByUsername(  loginDTO.getUsername()  );

        boolean valid = Security.checkPassord(loginDTO.getPassword(), passOnDB);
        if (! valid) {
            throw new RuntimeException("Username not found or passoword doesn't match");
        }

        Account account = new AccountService().findByUsername(  loginDTO.getUsername()  );
        String token =  Security.getToken( account.getDsUsename() );

        LOG.debug( token );

        response.header(Security.HEADER_AUTH,  Security.TOKEN_PREFIX.concat(token));

        return account;

    };

}
