/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saul
 */
@Entity
@Table(name = "sales_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesDetails.findAll", query = "SELECT s FROM SalesDetails s"),
    @NamedQuery(name = "SalesDetails.findByDetailId", query = "SELECT s FROM SalesDetails s WHERE s.detailId = :detailId"),
    @NamedQuery(name = "SalesDetails.findByQuantity", query = "SELECT s FROM SalesDetails s WHERE s.quantity = :quantity")})
public class SalesDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detail_id")
    private Integer detailId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private short quantity;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "sales_id", referencedColumnName = "order_num")
    @ManyToOne(optional = false)
    private SalesOrder salesId;

    public SalesDetails() {
    }

    public SalesDetails(Integer detailId) {
        this.detailId = detailId;
    }

    public SalesDetails(Integer detailId, short quantity) {
        this.detailId = detailId;
        this.quantity = quantity;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public SalesOrder getSalesId() {
        return salesId;
    }

    public void setSalesId(SalesOrder salesId) {
        this.salesId = salesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailId != null ? detailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesDetails)) {
            return false;
        }
        SalesDetails other = (SalesDetails) object;
        if ((this.detailId == null && other.detailId != null) || (this.detailId != null && !this.detailId.equals(other.detailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SalesDetails[ detailId=" + detailId + " ]";
    }
    
}
