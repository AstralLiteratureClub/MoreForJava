package bet.astral.more4jmc.annotations;

import java.lang.annotation.Documented;

@Documented
public @interface ScheduledForRemoval {
	String sinceMc();
	String inMc() default MinecraftVersion.UNKNOWN;
}
