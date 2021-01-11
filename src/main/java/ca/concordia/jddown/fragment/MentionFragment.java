package ca.concordia.jddown.fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MentionFragment extends Fragment {
    private static final String CHANNEL_PREFIX = "#";
    private static final String USER_NICKNAME_PREFIX = "@!";
    private static final String USER_PREFIX = "@";
    private static final String ROLE_PREFIX = "@&";

    private static final Pattern PATTERN = Pattern.compile("<(#|@!|@&|@)(\\d*?)>");

    public static enum Type {
        CHANNEL, USER, ROLE;

        public static Type from(String prefix) {
            if(prefix.equals(CHANNEL_PREFIX)) {
                return CHANNEL;
            } else if(prefix.equals(USER_NICKNAME_PREFIX) || prefix.equals(USER_PREFIX)) {
                return USER;
            } else if(prefix.equals(ROLE_PREFIX)) {
                return ROLE;
            } else {
                return null;
            }
        }

        public static String string(Type mentionType) {
            if (mentionType == null) {
                return null;
            }

            switch(mentionType) {
                case CHANNEL:
                    return "CHANNEL";
                case USER:
                    return "USER";
                case ROLE:
                    return "ROLE";
                default:
                    return null;
            }
        }
    }

    private Type mentionType;
    private String uuid;

    public MentionFragment() {
    }

    private MentionFragment(String string, int position, Type mentionType, String uuid) {
        super(string, position);
        this.mentionType = mentionType;
        this.uuid = uuid;
    }

    public Type getType() {
        return mentionType;
    }

    public String getUUID() {
        return uuid;
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        return new MentionFragment(matcher.group(), matcher.start(), Type.from(matcher.group(1)), matcher.group(2));
    }

    @Override
    public String toString() {
        return "MENTION{" + start() + ", " + Type.string(getType()) + ", " + getUUID() + "}";
    }

    @Override
    public String getText() {
        return getRawText();
    }
}
