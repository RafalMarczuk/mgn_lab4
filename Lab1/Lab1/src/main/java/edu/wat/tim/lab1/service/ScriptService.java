package edu.wat.tim.lab1.service;


import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.wat.tim.lab1.repository.CartEntityRepository;
import edu.wat.tim.lab1.repository.CustomerEntityRepository;
import edu.wat.tim.lab1.repository.ProductsEntityRepository;
import edu.wat.tim.lab1.repository.PositionInCartRepository;


@Service
@Slf4j
public class ScriptService {
    private final CustomerEntityRepository customerEntityRepository;
    private final CartEntityRepository cartEntityRepository;
    private final ProductsEntityRepository productsEntityRepository;
    private final PositionInCartRepository positionInCartRepository;


    @Autowired
    public ScriptService(CustomerEntityRepository customerEntityRepository, CartEntityRepository cartEntityRepository, PositionInCartRepository positionInCartRepository, ProductsEntityRepository productsEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
        this.cartEntityRepository = cartEntityRepository;
        this.positionInCartRepository = positionInCartRepository;
        this.productsEntityRepository = productsEntityRepository;
    }

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("customerEntityRepository", customerEntityRepository);
            bindings.putMember("cartEntityRepository", cartEntityRepository);
            bindings.putMember("positionInCartRepository", positionInCartRepository);
            bindings.putMember("productsEntityRepository", productsEntityRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }
}