# jax-rs
JAX-RS test
# REST
 * RESTとは分散型システムで優れた設計原則
 * Representational State Transfer: URIで一意に特定できるリソースがある。それは常に変化しうるし、見る人によって見え方も違う。見る人＝クライアントによって見える見え方がRepresentation。クライアントはサーバからもらうデータがRepresentation of Resourceである。これをクライアントが受け取ることでクライアントの状態＝stateが変化する=transfer。だからRepresentational State Transfer。
 * HTTPはRESTのひとつ
   * URIで一意に識別できるリソース
   * そのリソースを操作するための統一されたインタフェース
   * ステートレス：前回の操作やインタラクションに関係なく、同じリクエストに対して同じ結果が返ってくる。1回目と2回目で同じ操作をして同じ結果が返ってくるという意味ではないと思う。ネットワークのルーティングや処理するサーバに依存しないという意味だと思う。
   * いろいろなフォーマットで入出力できる

# Links
 * いまさら聞けないRESTの基礎知識
   * http://www.atmarkit.co.jp/ait/articles/1604/18/news020_2.html
 * RESTful Web サービスの基本 
   * https://www.ibm.com/developerworks/jp/webservices/library/ws-restful/
 * RESTアーキテクチャを最初に提唱したFielding博士の論文
   * http://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm
 * RESTのRepresentation State Transferが表す意味を解説する
   * http://www.slideshare.net/abhaymilestogo/representational-state-transfer-rest?next_slideshow=1 
 * WebSphereのJAX-RSでファイルアップロードを実装するには
   * https://www.ibm.com/developerworks/community/blogs/Dougclectica/entry/JAX_RS_in_WebSphere_to_upload_files?lang=ja
   * http://www.programcreek.com/java-api-examples/index.php?api=org.apache.wink.common.model.multipart.InPart
   * https://www.ibm.com/support/knowledgecenter/ja/SSAW57_8.5.5/com.ibm.websphere.nd.doc/ae/twbs_jaxrs_multipart_formdata_from_html.html
 * JAX-RSのリソースクラスのライフサイクル
   * https://www.ibm.com/support/knowledgecenter/ja/SSAW57_8.5.5/com.ibm.websphere.wlp.doc/ae/rwlp_jaxrs2.0_ejbcdi.html
