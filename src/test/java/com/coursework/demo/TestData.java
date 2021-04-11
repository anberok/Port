package com.coursework.demo;

import com.coursework.demo.dto.CrewDTO;
import com.coursework.demo.dto.HoldItemDTO;
import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.dto.OrderDTO;
import com.coursework.demo.dto.ShipDTO;
import com.coursework.demo.dto.WeaponDTO;
import com.coursework.demo.dto.WeaponsTypeDTO;
import com.coursework.demo.entity.Crew;
import com.coursework.demo.entity.HoldItem;
import com.coursework.demo.entity.Order;
import com.coursework.demo.entity.Ship;
import com.coursework.demo.entity.Weapon;
import com.coursework.demo.entity.enums.HoldType;
import com.coursework.demo.entity.enums.OrderStatus;
import com.coursework.demo.entity.enums.ShipType;
import com.coursework.demo.entity.enums.WeaponsType;
import com.coursework.demo.entity.enums.Wear;

import java.util.Arrays;
import java.util.List;

import static com.coursework.demo.entity.enums.HoldType.ALCOHOL;
import static com.coursework.demo.entity.enums.HoldType.CLOTH;
import static com.coursework.demo.entity.enums.HoldType.JEWELLERY;
import static com.coursework.demo.entity.enums.HoldType.PROVISIONS;
import static com.coursework.demo.entity.enums.OrderStatus.ANALYSIS;
import static com.coursework.demo.entity.enums.OrderStatus.DONE;
import static com.coursework.demo.entity.enums.OrderStatus.REJECTED;
import static com.coursework.demo.entity.enums.WeaponsType.CANNON;
import static com.coursework.demo.entity.enums.WeaponsType.CUTLASS;
import static com.coursework.demo.entity.enums.WeaponsType.MUSKET;

public class TestData {

    public static Crew getCrew() {
        return Crew.builder()
                .id(1L)
                .captain("Sparrow")
                .size(50)
                .nationality("Spanish")
                .build();
    }

    public static CrewDTO getCrewRequest() {
        return CrewDTO.builder()
                .id(1L)
                .captain("Sparrow")
                .size(50)
                .nationality("Spanish")
                .build();
    }

    public static HoldItem getHoldItem() {
        return HoldItem.builder()
                .id(1L)
                .holdType(HoldType.ALCOHOL)
                .name("Jack Daniels")
                .quantity(10)
                .build();
    }

    public static HoldItemDTO getHoldItemRequest() {
        return HoldItemDTO.builder()
                .id(1L)
                .holdType(HoldType.ALCOHOL)
                .name("Jack Daniels")
                .quantity(10)
                .build();
    }

    public static Order getOrder() {
        return Order.builder()
                .id(1L)
                .orderStatus(OrderStatus.ANALYSIS)
                .client("Barbossa")
                .reward(200)
                .build();
    }

    public static OrderDTO getOrderRequest() {
        return OrderDTO.builder()
                .id(1L)
                .orderStatus(OrderStatus.ANALYSIS)
                .client("Barbossa")
                .reward(200)
                .build();
    }

    public static Ship getShip() {
        return Ship.builder()
                .id(1L)
                .shipType(ShipType.BARQUE)
                .speed(200)
                .name("Victory")
                .build();
    }

    public static ShipDTO getShipRequest() {
        return ShipDTO.builder()
                .id(1L)
                .shipType(ShipType.BARQUE)
                .speed(200)
                .name("Victory")
                .build();
    }

    public static Weapon getWeapon() {
        return Weapon.builder()
                .id(1L)
                .quantity(5)
                .weaponsType(WeaponsType.BLUNDERBUS)
                .wear(Wear.MINIMAL_WEAR)
                .build();
    }

    public static WeaponDTO getWeaponRequest() {
        return WeaponDTO.builder()
                .id(1L)
                .quantity(5)
                .weaponsType(WeaponsType.BLUNDERBUS)
                .wear(Wear.MINIMAL_WEAR)
                .build();
    }

    public static List<HoldTypeDTO> getExpectedHoldTypeDTOList() {
        return Arrays.asList(
                HoldTypeDTO.builder().name(PROVISIONS).quantity(100L).build(),
                HoldTypeDTO.builder().name(CLOTH).quantity(400L).build(),
                HoldTypeDTO.builder().name(ALCOHOL).quantity(500L).build(),
                HoldTypeDTO.builder().name(JEWELLERY).quantity(100L).build()
        );
    }

    public static List<Order> getExpectedOrderList() {
        Crew crew = Crew.builder().id(1L).captain("Sparrow").nationality("Spanish").size(50).build();
        return Arrays.asList(
                Order.builder()
                        .id(1L)
                        .client("France")
                        .description("sink 5 english ships")
                        .orderStatus(DONE)
                        .reward(200)
                        .crew(crew)
                        .build(),
                Order.builder()
                        .id(2L)
                        .client("England")
                        .description("sink 5 france ships")
                        .orderStatus(REJECTED)
                        .reward(150)
                        .crew(crew)
                        .build(),
                Order.builder()
                        .id(3L)
                        .client("Spanish")
                        .description("sink 3 english ships")
                        .orderStatus(ANALYSIS)
                        .reward(100)
                        .crew(crew)
                        .build()
        );
    }

    public static List<WeaponsTypeDTO> getExpectedWeaponsTypeDTOList() {
        return Arrays.asList(
                WeaponsTypeDTO.builder().name(MUSKET).quantity(110L).build(),
                WeaponsTypeDTO.builder().name(CANNON).quantity(10L).build(),
                WeaponsTypeDTO.builder().name(CUTLASS).quantity(5L).build()
        );
    }
}
