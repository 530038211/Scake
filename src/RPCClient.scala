import java.net.Socket
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class RPCClient {
  //客服端链接服务器返回一个socket（套接字）
  def connect(host:String,port:Int):Socket = {
     new Socket(host,port);
  } 
}

object RPCClient {
  
  def main(args: Array[String]): Unit = {
      val rpcClient = new RPCClient;
      val socket = rpcClient.connect("127.0.0.1", 8888);
      //通信(发送消息)
      val write = new ObjectOutputStream(socket.getOutputStream);
      val read = new ObjectInputStream(socket.getInputStream);
      for(i<-0 to 10){
        write.writeObject(new RegistMsg("tom",i.toString()));
        //接受返回消息
        val response = read.readObject();
        response match{
          case ResultMsg(msg)=>{
            println(msg);
          }
        }
       
      }
      write.close();
      socket.close();
  }
}

