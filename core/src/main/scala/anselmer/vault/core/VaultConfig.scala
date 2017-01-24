package anselmer.vault.core

import java.net.URL

import dispatch.{Req, url}
import io.circe.generic.auto._
import io.circe.syntax._
import anselmer.scala.syntax.asyncresult._
import anselmer.scala.syntax.request._
import anselmer.scala.syntax.response._
import uscala.concurrent.result.AsyncResult

import scala.concurrent.ExecutionContext

case class VaultConfig(wsClient: WSClient, token: AsyncResult[String, String])
case class AppRole(role_id: String, secret_id: String)

object VaultConfig {

    def apply(client: WSClient, roleId: AppRole)(implicit ec: ExecutionContext): VaultConfig =
        VaultConfig(client,
            client.path("auth/approle/login").
                post(roleId.asJson).
                toAsyncResult.
                acceptStatusCodes(200).
                extractFromJson[String](_.downField("auth").downField("client_token")))

    def apply(wsClient: WSClient, token: String)(implicit ec: ExecutionContext): VaultConfig =
        VaultConfig(wsClient, AsyncResult.ok[String, String](token))
}



case class WSClient(server: URL,
    version: String = "v1") {
    def path(p: String): Req =
        url(s"${server.toString}/$version/$p").
            setContentType("application/json", "UTF-8")
}


