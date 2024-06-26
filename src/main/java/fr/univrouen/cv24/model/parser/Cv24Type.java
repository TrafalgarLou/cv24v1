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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cv24Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cv24Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identite" type="{http://univ.fr/cv24}identiteType"/&gt;
 *         &lt;element name="objectif" type="{http://univ.fr/cv24}objType"/&gt;
 *         &lt;element name="prof" type="{http://univ.fr/cv24}profType"/&gt;
 *         &lt;element name="competences" type="{http://univ.fr/cv24}compType"/&gt;
 *         &lt;element name="divers" type="{http://univ.fr/cv24}diversType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cv24Type", propOrder = {
    "identite",
    "objectif",
    "prof",
    "competences",
    "divers"
})
public class Cv24Type {

    @XmlElement(required = true)
    protected IdentiteType identite;
    @XmlElement(required = true)
    protected ObjType objectif;
    @XmlElement(required = true)
    protected ProfType prof;
    @XmlElement(required = true)
    protected CompType competences;
    protected DiversType divers;

    /**
     * Gets the value of the identite property.
     * 
     * @return
     *     possible object is
     *     {@link IdentiteType }
     *     
     */
    public IdentiteType getIdentite() {
        return identite;
    }

    /**
     * Sets the value of the identite property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentiteType }
     *     
     */
    public void setIdentite(IdentiteType value) {
        this.identite = value;
    }

    /**
     * Gets the value of the objectif property.
     * 
     * @return
     *     possible object is
     *     {@link ObjType }
     *     
     */
    public ObjType getObjectif() {
        return objectif;
    }

    /**
     * Sets the value of the objectif property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjType }
     *     
     */
    public void setObjectif(ObjType value) {
        this.objectif = value;
    }

    /**
     * Gets the value of the prof property.
     * 
     * @return
     *     possible object is
     *     {@link ProfType }
     *     
     */
    public ProfType getProf() {
        return prof;
    }

    /**
     * Sets the value of the prof property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfType }
     *     
     */
    public void setProf(ProfType value) {
        this.prof = value;
    }

    /**
     * Gets the value of the competences property.
     * 
     * @return
     *     possible object is
     *     {@link CompType }
     *     
     */
    public CompType getCompetences() {
        return competences;
    }

    /**
     * Sets the value of the competences property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompType }
     *     
     */
    public void setCompetences(CompType value) {
        this.competences = value;
    }

    /**
     * Gets the value of the divers property.
     * 
     * @return
     *     possible object is
     *     {@link DiversType }
     *     
     */
    public DiversType getDivers() {
        return divers;
    }

    /**
     * Sets the value of the divers property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiversType }
     *     
     */
    public void setDivers(DiversType value) {
        this.divers = value;
    }

}
