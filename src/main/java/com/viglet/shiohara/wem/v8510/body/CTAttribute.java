//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.17 at 02:23:34 PM BRT 
//


package com.viglet.shiohara.wem.v8510.body;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 *   ---------------------------------------------------------------------------
 *   - Name:  CT_attribute
 *   - Desc:  Defines the attributes and elements that make up a managed object
 *   -        attribute.
 *   ---------------------------------------------------------------------------
 *   			
 * 
 * <p>Java class for CT_attribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_attribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="valueBLOB">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueBoolean">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_booleanOrEmpty">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueCLOB">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueCompoundReference">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;extension base="{http://www.vignette.com/xmlschemas/importexport}CT_id">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueDate">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_longOrEmpty">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueDecimal">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_doubleOrEmpty">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueFilePath">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueGUID">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;extension base="{http://www.vignette.com/xmlschemas/importexport}CT_id">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueInt">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_intOrEmpty">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueNumerical">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_doubleOrEmpty">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueString">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="valueTinyInt">
 *             &lt;complexType>
 *               &lt;simpleContent>
 *                 &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_shortOrEmpty">
 *                   &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
 *                 &lt;/extension>
 *               &lt;/simpleContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_attribute", propOrder = {
    "valueBLOB",
    "valueBoolean",
    "valueCLOB",
    "valueCompoundReference",
    "valueDate",
    "valueDecimal",
    "valueFilePath",
    "valueGUID",
    "valueInt",
    "valueNumerical",
    "valueString",
    "valueTinyInt"
})
public class CTAttribute {

    protected CTAttribute.ValueBLOB valueBLOB;
    protected CTAttribute.ValueBoolean valueBoolean;
    protected CTAttribute.ValueCLOB valueCLOB;
    protected CTAttribute.ValueCompoundReference valueCompoundReference;
    protected CTAttribute.ValueDate valueDate;
    protected CTAttribute.ValueDecimal valueDecimal;
    protected CTAttribute.ValueFilePath valueFilePath;
    protected CTAttribute.ValueGUID valueGUID;
    protected CTAttribute.ValueInt valueInt;
    protected CTAttribute.ValueNumerical valueNumerical;
    protected CTAttribute.ValueString valueString;
    protected CTAttribute.ValueTinyInt valueTinyInt;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the valueBLOB property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueBLOB }
     *     
     */
    public CTAttribute.ValueBLOB getValueBLOB() {
        return valueBLOB;
    }

    /**
     * Sets the value of the valueBLOB property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueBLOB }
     *     
     */
    public void setValueBLOB(CTAttribute.ValueBLOB value) {
        this.valueBLOB = value;
    }

    /**
     * Gets the value of the valueBoolean property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueBoolean }
     *     
     */
    public CTAttribute.ValueBoolean getValueBoolean() {
        return valueBoolean;
    }

    /**
     * Sets the value of the valueBoolean property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueBoolean }
     *     
     */
    public void setValueBoolean(CTAttribute.ValueBoolean value) {
        this.valueBoolean = value;
    }

    /**
     * Gets the value of the valueCLOB property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueCLOB }
     *     
     */
    public CTAttribute.ValueCLOB getValueCLOB() {
        return valueCLOB;
    }

    /**
     * Sets the value of the valueCLOB property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueCLOB }
     *     
     */
    public void setValueCLOB(CTAttribute.ValueCLOB value) {
        this.valueCLOB = value;
    }

    /**
     * Gets the value of the valueCompoundReference property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueCompoundReference }
     *     
     */
    public CTAttribute.ValueCompoundReference getValueCompoundReference() {
        return valueCompoundReference;
    }

    /**
     * Sets the value of the valueCompoundReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueCompoundReference }
     *     
     */
    public void setValueCompoundReference(CTAttribute.ValueCompoundReference value) {
        this.valueCompoundReference = value;
    }

    /**
     * Gets the value of the valueDate property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueDate }
     *     
     */
    public CTAttribute.ValueDate getValueDate() {
        return valueDate;
    }

    /**
     * Sets the value of the valueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueDate }
     *     
     */
    public void setValueDate(CTAttribute.ValueDate value) {
        this.valueDate = value;
    }

    /**
     * Gets the value of the valueDecimal property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueDecimal }
     *     
     */
    public CTAttribute.ValueDecimal getValueDecimal() {
        return valueDecimal;
    }

    /**
     * Sets the value of the valueDecimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueDecimal }
     *     
     */
    public void setValueDecimal(CTAttribute.ValueDecimal value) {
        this.valueDecimal = value;
    }

    /**
     * Gets the value of the valueFilePath property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueFilePath }
     *     
     */
    public CTAttribute.ValueFilePath getValueFilePath() {
        return valueFilePath;
    }

    /**
     * Sets the value of the valueFilePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueFilePath }
     *     
     */
    public void setValueFilePath(CTAttribute.ValueFilePath value) {
        this.valueFilePath = value;
    }

    /**
     * Gets the value of the valueGUID property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueGUID }
     *     
     */
    public CTAttribute.ValueGUID getValueGUID() {
        return valueGUID;
    }

    /**
     * Sets the value of the valueGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueGUID }
     *     
     */
    public void setValueGUID(CTAttribute.ValueGUID value) {
        this.valueGUID = value;
    }

    /**
     * Gets the value of the valueInt property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueInt }
     *     
     */
    public CTAttribute.ValueInt getValueInt() {
        return valueInt;
    }

    /**
     * Sets the value of the valueInt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueInt }
     *     
     */
    public void setValueInt(CTAttribute.ValueInt value) {
        this.valueInt = value;
    }

    /**
     * Gets the value of the valueNumerical property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueNumerical }
     *     
     */
    public CTAttribute.ValueNumerical getValueNumerical() {
        return valueNumerical;
    }

    /**
     * Sets the value of the valueNumerical property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueNumerical }
     *     
     */
    public void setValueNumerical(CTAttribute.ValueNumerical value) {
        this.valueNumerical = value;
    }

    /**
     * Gets the value of the valueString property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueString }
     *     
     */
    public CTAttribute.ValueString getValueString() {
        return valueString;
    }

    /**
     * Sets the value of the valueString property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueString }
     *     
     */
    public void setValueString(CTAttribute.ValueString value) {
        this.valueString = value;
    }

    /**
     * Gets the value of the valueTinyInt property.
     * 
     * @return
     *     possible object is
     *     {@link CTAttribute.ValueTinyInt }
     *     
     */
    public CTAttribute.ValueTinyInt getValueTinyInt() {
        return valueTinyInt;
    }

    /**
     * Sets the value of the valueTinyInt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAttribute.ValueTinyInt }
     *     
     */
    public void setValueTinyInt(CTAttribute.ValueTinyInt value) {
        this.valueTinyInt = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueBLOB {

        @XmlValue
        protected byte[] value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setValue(byte[] value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_booleanOrEmpty">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueBoolean {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * 
         *   ---------------------------------------------------------------------------
         *   - Name:  ST_booleanOrEmpty
         *   - Desc:  Simple element type, accepting either a boolean value or an
         *   -        empty element.
         *   ---------------------------------------------------------------------------
         * 			
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueCLOB {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.vignette.com/xmlschemas/importexport}CT_id">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ValueCompoundReference
        extends CTId
    {

        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_longOrEmpty">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueDate {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * 
         *   ---------------------------------------------------------------------------
         *   - Name:  ST_longOrEmpty
         *   - Desc:  Simple element type, accepting either a long value or an
         *   -        empty element.
         *   ---------------------------------------------------------------------------
         * 			
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_doubleOrEmpty">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueDecimal {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * 
         *   ---------------------------------------------------------------------------
         *   - Name:  ST_doubleOrEmpty
         *   - Desc:  Simple element type, accepting either a double value or an
         *   -        empty element.
         *   ---------------------------------------------------------------------------
         * 			
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueFilePath {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.vignette.com/xmlschemas/importexport}CT_id">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ValueGUID
        extends CTId
    {

        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_intOrEmpty">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueInt {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * 
         *   ---------------------------------------------------------------------------
         *   - Name:  ST_intOrEmpty
         *   - Desc:  Simple element type, accepting either an integer value or an
         *   -        empty element.
         *   ---------------------------------------------------------------------------
         * 			
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_doubleOrEmpty">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueNumerical {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * 
         *   ---------------------------------------------------------------------------
         *   - Name:  ST_doubleOrEmpty
         *   - Desc:  Simple element type, accepting either a double value or an
         *   -        empty element.
         *   ---------------------------------------------------------------------------
         * 			
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueString {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.vignette.com/xmlschemas/importexport>ST_shortOrEmpty">
     *       &lt;attGroup ref="{http://www.vignette.com/xmlschemas/importexport}AG_valueAttributes"/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ValueTinyInt {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "isNull")
        protected Boolean isNull;

        /**
         * 
         *   ---------------------------------------------------------------------------
         *   - Name:  ST_shortOrEmpty
         *   - Desc:  Simple element type, accepting either a short value or an
         *   -        empty element.
         *   ---------------------------------------------------------------------------
         * 			
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the isNull property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNull() {
            if (isNull == null) {
                return false;
            } else {
                return isNull;
            }
        }

        /**
         * Sets the value of the isNull property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNull(Boolean value) {
            this.isNull = value;
        }

    }

}