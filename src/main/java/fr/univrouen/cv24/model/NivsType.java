//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.05.17 at 06:53:20 PM UTC 
//


package fr.univrouen.cv24.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for nivsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="nivsType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="A1"/&gt;
 *     &lt;enumeration value="A2"/&gt;
 *     &lt;enumeration value="B1"/&gt;
 *     &lt;enumeration value="B2"/&gt;
 *     &lt;enumeration value="C1"/&gt;
 *     &lt;enumeration value="C2"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "nivsType")
@XmlEnum
public enum NivsType {

    @XmlEnumValue("A1")
    A_1("A1"),
    @XmlEnumValue("A2")
    A_2("A2"),
    @XmlEnumValue("B1")
    B_1("B1"),
    @XmlEnumValue("B2")
    B_2("B2"),
    @XmlEnumValue("C1")
    C_1("C1"),
    @XmlEnumValue("C2")
    C_2("C2");
    private final String value;

    NivsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NivsType fromValue(String v) {
        for (NivsType c: NivsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
