import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * 数据实体类
 */
public class BankData {
    LocalDate createTime;
    double price;
    String description;

    public BankData(LocalDate createTime, double price, String description) {
        this.createTime = createTime;
        this.price = price;
        this.description = description;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


   @Override
   public String toString() {
       return "bankCalculation{" +
               "date=" + createTime +
               ", total=" + price +
               ",description='" + description + '}';
   }

    public Notification validate() {
        final Notification notification = new Notification();

        final LocalDate parsedDate;
        try {
            parsedDate = this.createTime;
            if (parsedDate.isAfter(LocalDate.now())) {
                notification.addError("date cannot be in the future");
            }
        } catch (DateTimeException e) {
            notification.addError("Invalid format for date");
        }

        if (this.description.length() > 100) {
            notification.addError("this description is too long");
        }

        final double amount;
        try {
            amount = this.price;
        } catch (NumberFormatException e) {
            notification.addError("Invalid format for amount");
        }
        return notification;
    }
}
