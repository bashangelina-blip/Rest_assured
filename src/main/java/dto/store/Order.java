package dto.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private Long id;
    private Long petId;
    private Integer quantity;
    private ShipStatus status;
    private boolean complete;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime shipDate;

}
