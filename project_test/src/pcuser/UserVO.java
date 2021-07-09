package pcuser;

public class UserVO {
  String userID;
  String name;
  String pwd;
  int remain = 0;

  @Override
  public String toString() {
    return userID + ", name=" + name + ", pwd=" + pwd + ", remain=" + remain;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public int getRemain() {
    return remain;
  }

  public void setRemain(int remain) {
    this.remain = remain;
  }


}
