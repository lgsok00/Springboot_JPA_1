package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

  @Id @GeneratedValue
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;  // 주문 회원

  // JPQL select o from order o; -> SQL select * from order => n + 1

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // CascadeType.ALL => 상위 엔터티에서 하위 엔터티로 모든 작업을 전파
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "delivery_id")
  private Delivery delivery;  // 배송 정보

  // order_date
  private LocalDateTime orderDate; // 주문 시간

  @Enumerated(EnumType.STRING)
  private OrderStatus status; // 주문 상태 (ORDER, CANCEL)

  // == 연관관계 편의 메서드 == //
  public void setMember(Member member) {
    this.member = member;
    member.getOrders().add(this);
  }
  public void addOrderItem(OrderItem orderItem) {
    orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
    delivery.setOrder(this);
  }

}
