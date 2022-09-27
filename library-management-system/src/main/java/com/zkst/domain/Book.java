package com.zkst.domain;


public class Book {
   private  Integer id;
   private  String bookNumber;
   private  String bookName;
   private  String author;
   private  String price;
   private  String description;
   private  String status;

   public String getBookNumber() {
      return bookNumber;
   }

   public void setBookNumber(String bookNumber) {
      this.bookNumber = bookNumber;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getBookName() {
      return bookName;
   }

   public void setBookName(String bookName) {
      this.bookName = bookName;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   @Override
   public String toString() {
      return "Book{" +
              "id=" + id +
              ", bookNumber='" + bookNumber + '\'' +
              ", bookName='" + bookName + '\'' +
              ", author='" + author + '\'' +
              ", price='" + price + '\'' +
              ", description='" + description + '\'' +
              ", status='" + status + '\'' +
              '}';
   }
}
