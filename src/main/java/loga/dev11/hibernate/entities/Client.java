package loga.dev11.hibernate.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 200)
    private String name;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client='" + name + '\'' +
                '}';
    }

}