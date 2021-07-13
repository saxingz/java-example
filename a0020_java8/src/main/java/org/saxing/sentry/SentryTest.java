package org.saxing.sentry;

import io.sentry.ITransaction;
import io.sentry.Sentry;
import io.sentry.SentryLevel;
import io.sentry.SpanStatus;

import java.util.UUID;

public class SentryTest {

    public static void main(String[] args) throws InterruptedException {
        Sentry.init(options -> {
            options.setDsn("https://xxxxx@sentry.xxxxx.net/7");
            // Set traces_sample_rate to 1.0 to capture 100% of transactions for performance monitoring.
            // We recommend adjusting this value in production.
            options.setTracesSampleRate(1.0);
            // When first trying Sentry it's good to see what the SDK is doing:
            options.setDebug(true);
            options.setEnvironment("pro");
            options.setRelease("2.25.0");
            options.setMaxCacheItems(10);
            options.setMaxAttachmentSize(10240);

        });


        for (int i = 0; i < 1; i++) {
            Thread.sleep(500);
            String uuid = UUID.randomUUID().toString();
//            Sentry.captureMessage("hello saxing, " + uuid, SentryLevel.INFO);
            Sentry.captureException(new RuntimeException("test exception"));
        }


//        try {
//            throw new Exception("This is a test.");
//        } catch (Exception e) {
//            Sentry.captureException(e);
//        }


//        ITransaction transaction = Sentry.startTransaction("processOrderBatch()", "task");
//        try {
//            processOrderBatch();
//        } catch (Exception e) {
//            transaction.setThrowable(e);
//            transaction.setStatus(SpanStatus.INTERNAL_ERROR);
//            throw e;
//        } finally {
//            transaction.finish();
//        }

    }

}
