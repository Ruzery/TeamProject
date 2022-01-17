package api.parsing;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParsing {

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	public static void main(String[] args) {
		int page = 1; // 페이지 초기값
		try {
			while (true) {
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?serviceKey=zNMFT9xkUUxXXQjB%2FtbLdXjPI1hI9Il6aKqCZBfPELN2XnrGG2bkpO%2BcPzN%2ByaVRh5pdY2312vqwwbjYG6Mgag%3D%3D&MobileOS=ETC&MobileApp=AppTest&arrange=A&listYN=Y&eventStartDate=20170901&pageNo="+ page;

				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				// root tag
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
//				System.out.println("파싱할 리스트 수 : "+ nList.getLength());

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						System.out.println("######################");
						// System.out.println(eElement.getTextContent());
						System.out.println("주소  : " + getTagValue("addr1", eElement));
//						System.out.println("상품 코드  : " + getTagValue("areacode", eElement));
						System.out.println("상품명 : " + getTagValue("contentid", eElement));
						System.out.println("GPS X좌표  : " + getTagValue("mapx", eElement));
						System.out.println("GPS Y좌표  : " + getTagValue("mapy", eElement));
						System.out.println("전화번호  : " + getTagValue("tel", eElement));
						System.out.println("제목  : " + getTagValue("title", eElement));
					} // for end
				} // if end

				page += 1;
				System.out.println("page number : " + page);
				if (page > 6000) {
					break;
				}
			} // while end

		} catch (Exception e) {
			e.printStackTrace();
		} // try~catch end
	} // main end
} // class end