//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.05.17 at 07:11:34 PM UTC 
//


package fr.univrouen.cv24.model.parser;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lvType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lvType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="lang" type="{http://univ.fr/cv24}iso639-1Type" /&gt;
 *       &lt;attribute name="cert" use="required" type="{http://univ.fr/cv24}certificationType" /&gt;
 *       &lt;attribute name="nivs" type="{http://univ.fr/cv24}nivsType" /&gt;
 *       &lt;attribute name="nivi" type="{http://univ.fr/cv24}niviType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lvType")
public class LvType {

    @XmlAttribute(name = "lang")
    protected String lang;
    @XmlAttribute(name = "cert", required = true)
    protected CertificationType cert;
    @XmlAttribute(name = "nivs")
    protected NivsType nivs;
    @XmlAttribute(name = "nivi")
    protected Integer nivi;

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the cert property.
     * 
     * @return
     *     possible object is
     *     {@link CertificationType }
     *     
     */
    public CertificationType getCert() {
        return cert;
    }

    /**
     * Sets the value of the cert property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificationType }
     *     
     */
    public void setCert(CertificationType value) {
        this.cert = value;
    }

    /**
     * Gets the value of the nivs property.
     * 
     * @return
     *     possible object is
     *     {@link NivsType }
     *     
     */
    public NivsType getNivs() {
        return nivs;
    }

    /**
     * Sets the value of the nivs property.
     * 
     * @param value
     *     allowed object is
     *     {@link NivsType }
     *     
     */
    public void setNivs(NivsType value) {
        this.nivs = value;
    }

    /**
     * Gets the value of the nivi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNivi() {
        return nivi;
    }

    /**
     * Sets the value of the nivi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNivi(Integer value) {
        this.nivi = value;
    }

}