package model.xml;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Adrien Castex
 * @see https://github.com/AstartesGuardian/WebDAV-Server/blob/c33b5e127d705cf939407b0aca2157dfe80854f3/WebDAV-Server/src/webdav/server/NodeListWrap.java
 * @since 19 Apr 2015
 */
public class NodeListWrap implements Iterator<Node>
{
    private NodeListWrap(NodeList list)
    {
        this.list = list;
        this.maxIndex = list.getLength();
        this.index = 0;
    }
    private final NodeList list;
    private final int maxIndex;
    private int index;
    
    /**
     * Get stream for the list of node in parameter. 
     * @param list Node List.
     * @return list of node convert in stream.
     */
    public static Stream<Node> getStream(NodeList list)
    {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(new NodeListWrap(list), Spliterator.NONNULL),
                false);
    }
    
    /**
     * Get bytes of the document in parameter.
     * @param doc Document.
     * @return array of bytes.
     */
    public static byte[] getBytes(Document doc)
    {
        try
        {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(boas);

            transformer.transform(source, result);

            return boas.toByteArray();
        }
        catch (TransformerException ex)
        {
            return new byte[0];
        }
    }
    
    

    @Override
    public boolean hasNext()
    {
        return index < maxIndex;
    }

    @Override
    public Node next()
    {
        return list.item(index++);
    }
}
