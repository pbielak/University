package pl.bielak.mydropbox.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import pl.bielak.mydropbox.config.ConfigService;
import static pl.bielak.mydropbox.config.Keys.DROPBOX_TOKEN_KEY;
import static pl.bielak.mydropbox.config.Keys.PROPERTIES_FILE_PATH_KEY;

public class DropboxConnector {
  private static final String TOKEN;
  private static DbxClientV2 client;

  static  {
    TOKEN = new ConfigService(PROPERTIES_FILE_PATH_KEY).getStringProperty(DROPBOX_TOKEN_KEY);
    DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("dropbox/MySimpleJavaClient", "pl_PL");
    client = new DbxClientV2(dbxRequestConfig, TOKEN);
  }

  public static DbxClientV2 getClientInstance() {
    return client;
  }
}
