package bet.astral.more4j.registry;

import bet.astral.more4j.registry.exception.RegistryMutationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Simple implementation of registry which can be mutated
 * @param <T> the type of value
 */
public class MutableRegistry<T> implements Registry<T> {
	private final Map<RegistryKey, T> registeredValues = new HashMap<>();
	private boolean acceptsRegisterations = true;

	/**
	 * Registers given value to the registry key in registry.
	 * Throws {@link RegistryMutationException} is no registrations are accepted
	 * @param registryKey key
	 * @param value value
	 * @return value
	 * @throws RegistryMutationException if no registerations are accepted
	 */
	public T register(@NotNull RegistryKey registryKey, @NotNull T value) throws RegistryMutationException {
		if (!acceptsRegisterations()){
			throw new RegistryMutationException(value, registryKey);
		}
		registeredValues.put(registryKey, value);
		return value;
	}

	/**
	 * Checks if the given registry key can register anything
	 * @param registryKey registry key
	 * @return true if registered
	 */
	@Override
	public boolean isRegistered(@NotNull RegistryKey registryKey) {
		return registeredValues.containsKey(registryKey);
	}

	/**
	 * Locks the registry so no new registry values can be registered
	 */
	public void lock(){
		acceptsRegisterations = false;
	}


	@Override
	public boolean acceptsRegisterations() {
		return acceptsRegisterations;
	}

	@Override
	public @Nullable T getValue(@NotNull RegistryKey registryKey) {
		return registeredValues.get(registryKey);
	}

	@Override
	public @Nullable T getValue(@NotNull Registrable<?> registrable) {
		return registeredValues.get(registrable.getRegistryKey());
	}

	@Override
	public @NotNull Iterator<T> iterator() {
		return registeredValues.values().iterator();
	}
}
