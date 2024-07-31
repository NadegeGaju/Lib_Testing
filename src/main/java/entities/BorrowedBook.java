package entities;

import java.time.LocalDate;

public class BorrowedBook {

    private int userId;
    private Long bookId;
    private LocalDate borrowDate;

    public BorrowedBook(int userId, Long bookId, LocalDate borrowDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
    }

    public int getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
