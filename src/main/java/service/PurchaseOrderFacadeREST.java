/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entidades.Product;
import entidades.PurchaseOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Saul
 */
@Stateless
@Path("purchaseorder")
public class PurchaseOrderFacadeREST extends AbstractFacade<PurchaseOrder> {
    @PersistenceContext(unitName = "com.mycompany_StoreBackend_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PurchaseOrderFacadeREST() {
        super(PurchaseOrder.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(PurchaseOrder entity) {
        PurchaseOrder purchase = entity;
        int quantity = purchase.getQuantity();
        System.out.print("Cantidad de productos comprados = "+quantity);
        Product product = purchase.getProductId();
        System.out.print("Cantidad de productos disponibles = "+product.getQuantityOnHand());
        product.setQuantityOnHand(product.getQuantityOnHand()+quantity);
        System.out.print("Cantidad de productos disponibles luego de la compra = "+product.getQuantityOnHand());
        em.merge(product);
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, PurchaseOrder entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public PurchaseOrder find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<PurchaseOrder> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<PurchaseOrder> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
