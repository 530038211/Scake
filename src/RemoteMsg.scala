

trait RemoteMsg extends Serializable

//注册信息 (用于模式匹配的)
case class RegistMsg(account:String,password:String) extends RemoteMsg
//心跳信息
case class HeartBeatMsg(cientId:String ,message:String) extends RemoteMsg
//返回消息
case class ResultMsg(result:String) extends RemoteMsg
