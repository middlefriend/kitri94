package hist;

public class HistVO {
  String histId = null;
  String userId = null;
  String status = null;
  int seat = 0;
  String dateTime = null;

  @Override
  public String toString() {
    return "histId=" + histId + ", userId=" + userId + ", status=" + status + ", seat=" + seat
        + ", dateTime=" + dateTime;
  }

  public String getHistId() {
    return histId;
  }

  public void setHistId(String histId) {
    this.histId = histId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getSeat() {
    return seat;
  }

  public void setSeat(int seat) {
    this.seat = seat;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String datetime) {
    this.dateTime = datetime;
  }



}
