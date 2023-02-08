package ru.bvkuchin.market.Market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.bvkuchin.market.Market.services.ProductServise;
import ru.bvkuchin.market.Market.soap.products.*;

@Endpoint
@RequiredArgsConstructor
public class ProductEndPoint {

    private static final String NAMESPACE_URI = "http://www.bvkuchin.ru/market/products";
    private final ProductServise productServise;



//  http://localhost:8189/market/ws
//    Header -> Content-Type: text/xml
    /*

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.bvkuchin.ru/market/products">
    <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByNameRequest>
                    <f:name>Хлеб</f:name>
                </f:getProductByNameRequest>
            </soapenv:Body>
        </soapenv:Envelope>
*/
//


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByNameRequest")
    @ResponsePayload
    public GetProductByNameResponse getProductByName(@RequestPayload GetProductByNameRequest request) {
        GetProductByNameResponse response = new GetProductByNameResponse();
        response.setProduct(productServise.getByName(request.getName()));
        return response;
    }



//  http://localhost:8189/market/ws
//    Header -> Content-Type: text/xml
//
//
//        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.bvkuchin.ru/market/products">
//            <soapenv:Header/>
//            <soapenv:Body>
//                <f:getAllProductsRequest/>
//            </soapenv:Body>
//        </soapenv:Envelope>


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        System.out.println("test");
        GetAllProductsResponse response = new GetAllProductsResponse();
        productServise.getAllStudents().forEach(product -> {
            ProductSoap ps = new ProductSoap();
            ps.setId(product.getId());
            ps.setPrice(product.getPrice());
            ps.setName(product.getName());
            response.getProducts().add(ps);
        });

        return response;
    }

}
