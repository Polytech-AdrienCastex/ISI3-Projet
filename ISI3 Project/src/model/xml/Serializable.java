package model.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Convert an instance to an Element (xml element)
 */
public interface Serializable
{
    /**
     * Convert the current instance to a XML element
     * @param elementBuilder
     * @return the XML element representing the current instance
     */
    public Element toXML(Document elementBuilder);
}
