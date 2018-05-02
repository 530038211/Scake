import java.net.{ServerSocket, Socket}
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class RPCServer{
   //服务器绑定端口
   def bind(port:Int):Unit = {
       //1.创建serverSocket，端口需和客户端一致，接受客服端的请求
       //2.服务器端需要创建监听特定端口的ServerSocket，ServerSocket负责接收客户连接请求
       val ss =  new ServerSocket(port);
       //服务器接收客户端请求
       val socket = ss.accept();
       //接收的信息
       val read:ObjectInputStream = new ObjectInputStream(socket.getInputStream)
       val write = new ObjectOutputStream(socket.getOutputStream)
       
       for(i<-0 to 10){
        val msg = read.readObject()
        val rmsg = msg.asInstanceOf[RemoteMsg]
        //进行模式匹配 (模式匹配的类型要加case)
            val result = rmsg match{
                case RegistMsg(account:String,password:String)=>{
                  handlerRegister(account,password)
                } 
                case HeartBeatMsg(cientId:String ,message:String)=>{
                  handlerRegister(cientId,message)
                } 
            }
            write.writeObject(result)
            write.flush()
       }
      
  }
   
    //处理注册消息
  private def handlerRegister(account: String, password: String): ResultMsg = {
    println(s"a register msg from client: acount $account password: $password")
    ResultMsg("register success")
  } 
}

//伴生对象
object RPCServer {
  def main(args: Array[String]): Unit = {
      val rpcServer = new RPCServer;
      rpcServer.bind(8888);
  }
}
