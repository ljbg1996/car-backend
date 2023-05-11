package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public record ProductConfiguration(Product product, int duration) {
}
