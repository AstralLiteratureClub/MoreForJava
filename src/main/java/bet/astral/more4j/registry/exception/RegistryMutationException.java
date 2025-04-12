package bet.astral.more4j.registry.exception;

import bet.astral.more4j.registry.RegistryKey;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when registry is being mutated, but it cannot be mutated anymore
 */
public class RegistryMutationException extends RuntimeException {
  /**
   * @param value value
   * @param key registry key used in registration
   * @param <T> the type of the value
   */
  public <T> RegistryMutationException(@NotNull T value, @NotNull RegistryKey key) {
    super("Tried to mutate registry while registry does not accept new registrations. (Value: " + value + ", Key: " + key + ") ");
  }
}