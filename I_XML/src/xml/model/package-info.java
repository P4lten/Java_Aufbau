
// Das Binding soll auf die Felder der Klassen abzielen
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlJavaTypeAdapter(LocalDateAdapter.class)
@XmlJavaTypeAdapters({@XmlJavaTypeAdapter(LocalDateAdapter.class)})
package xml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import xml.adapters.LocalDateAdapter;
