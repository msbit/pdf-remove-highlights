package au.com.msbit.remove_annotations;

import java.util.Optional;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

class IsEmpty extends BaseMatcher<Optional> {
  public boolean matches(Object item) {
    if (item instanceof Optional optional) {
      return optional.isEmpty();
    }

    return false;
  }

  public void describeTo(Description description) {}
}

class IsPresent extends BaseMatcher<Optional> {
  public boolean matches(Object item) {
    if (item instanceof Optional optional) {
      return optional.isPresent();
    }

    return false;
  }

  public void describeTo(Description description) {}
}
