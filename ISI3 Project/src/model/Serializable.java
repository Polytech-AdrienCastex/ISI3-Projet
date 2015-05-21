package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 */
public interface Serializable
{
    public Element toXML(Document elementBuilder);
}
